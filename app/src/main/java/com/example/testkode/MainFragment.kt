package com.example.testkode

    import android.annotation.SuppressLint
    import android.app.Dialog
    import android.content.Context
    import android.graphics.Color
    import android.graphics.drawable.ColorDrawable
    import android.net.ConnectivityManager
    import android.net.NetworkCapabilities
    import android.os.Bundle
    import android.os.Handler
    import android.util.Log
    import android.view.*
    import android.widget.*
    import androidx.core.os.postDelayed
    import androidx.fragment.app.Fragment
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModelProvider
    import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    import androidx.viewpager2.widget.ViewPager2
    import com.example.testkode.adapter.ListAdapter
    import com.example.testkode.models.User
    import com.example.testkode.utils.apiErrorSnackBar.ApiSnackBar
    import com.example.testkode.viewModel.MainViewModel
    import com.google.android.material.tabs.TabLayout
    import com.google.android.material.tabs.TabLayoutMediator
    import com.example.testkode.utils.customSnackBar.CustomSnackBar
    import com.example.testkode.utils.loadingSnackBar.LoadingSnackBar


class MainFragment() : Fragment() {

    private var searchUserList: ArrayList<User> = arrayListOf()
    lateinit var viewModel: MainViewModel
    private var allUserList: ArrayList<User> = arrayListOf()
    private var searchLiveData: MutableLiveData<List<User>> = MutableLiveData()
    var error = false
//    private lateinit var users: UserList

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_main, container, false)
        val filter = view.findViewById<ImageButton>(R.id.filter)
        val refresh = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        refresh.setColorSchemeColors(Color.DKGRAY, Color.GRAY, Color.GRAY)
        val handler = Handler()
        refresh.setOnRefreshListener {
            if (!isOnline(requireContext())) {
                    view?.let {
                        CustomSnackBar
                            .make(it)
                            .show()
                    }
            }
            view?.let {
                LoadingSnackBar
                    .make(it)
                    .show()
            }
            handler.postDelayed(2500) {
                refresh.isRefreshing = false
                viewModel.getUsersData()
            }
        }

        if (!isOnline(requireContext())) {
            view?.let {
                CustomSnackBar
                    .make(it)
                    .show()
            }
        }

        filter.setOnClickListener {
            showDialog()
        }
        view?.let {
            LoadingSnackBar
                .make(it)
                .show()
        }
        handler.postDelayed(2500) {
            refresh.isRefreshing = false
            initViewModel()
        }

        if (error) {
            view = inflater.inflate(R.layout.error, container, false)
            val back = view.findViewById<Button>(R.id.back)
            back.setOnClickListener {
                view = inflater.inflate(R.layout.fragment_analytics, container, false)
            }
        }

        //SearchView

//        val searchView = view.findViewById<SearchView>(R.id.search_view)
//
//        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
//
//            var temp = viewModel.recyclerListData.value
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                viewModel.recyclerListData.postValue(temp)
//                return false
//            }
//
//            override fun onQueryTextSubmit(newText: String?): Boolean {
//                searchUserList.clear()
//                if (newText != null) {
//                    if(newText.isNotEmpty()){
//                        allUserList.forEach {
//                            if(it.firstName.contains(newText, ignoreCase = true ) || it.department.contains(newText, ignoreCase = true)|| it.userTag.contains(newText, ignoreCase = true) || it.lastName.contains(newText, ignoreCase = true)){
//                                searchUserList.add(it)
//                                val something = viewModel.recyclerListData.value
//                                val _temp = searchUserList.toList()
//                                if(something != null){
//                                    something.items = _temp
//                                }
//
//                                viewModel.recyclerListData.apply {
//                                    this.value = something
//                                }
//                            }
//                        }
//
//                    } else {
//                        searchUserList.clear()
//                        searchUserList.addAll(allUserList)
//                        val _temp = allUserList.toList()
//                        val something = viewModel.recyclerListData.value
//                        if(something != null){
//                            something.items = _temp
//                        }
//
//                        viewModel.recyclerListData.apply{
//                            this.value = something
//                        }
//                    }
//
//                }
//                return false
//            }
//        })
//        val searchView = view.findViewById<SearchView>(R.id.search_view)
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            var userList: List<User> = users.items
//            override fun onQueryTextSubmit(text: String?): Boolean {
//                if (text != null) {
//                    if(text.isNotEmpty()){
//                        userList.forEach {
//                            if (it.firstName.contains(text, ignoreCase = true) || it.lastName.contains(text, ignoreCase = true) || it.birthday.contains(text, ignoreCase = true) || it.userTag.contains(text, ignoreCase = true)){
//                                searchUserList.add(it)
//                                searchLiveData.apply {
//                                    this.value = searchUserList
//                                }
//                            }
//                        }
//                    }
//                }
//                return false
//            }
//
//            override fun onQueryTextChange(text: String?): Boolean {
//                searchUserList.clear()
//
//                if (text != null) {
//                    if(text.isNotEmpty()){
//                        userList.forEach {
//                            if (it.firstName.contains(text, ignoreCase = true) || it.lastName.contains(text, ignoreCase = true) || it.birthday.contains(text, ignoreCase = true) || it.userTag.contains(text, ignoreCase = true)){
//                                searchUserList.add(it)
//                                searchLiveData.apply {
//                                    this.value = searchUserList
//                                }
//                            }
//                        }
//                    }
//                }
//                return false
//            }
//        })


        // список
        val tabLayout = view.findViewById<TabLayout>(R.id.TabLayout)
        val viewPager = view.findViewById<ViewPager2>(R.id.ViewPager)

        val adapter = ListAdapter(childFragmentManager, this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Все"
                1 -> tab.text = "Designers"
                2 -> tab.text = "Analysts"
                3 -> tab.text = "Managers"
                4 -> tab.text = "IOS"
                5 -> tab.text = "Android"
                6 -> tab.text = "Frontend"
                7 -> tab.text = "Backend"
                8 -> tab.text = "QA"
                9 -> tab.text = "HR"
                10 -> tab.text = "PR"
                11 -> tab.text = "Supports"
                12 -> tab.text = "BackOffice"
            }
        }.attach()
        return view
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getUserList().observe(viewLifecycleOwner, {
            if (it != null) {

            } else {
                view?.let {
                    ApiSnackBar
                        .make(requireView())
                        .show()
                }
            }
        })
        viewModel.getUsersData()
    }

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.fragment_bottom_dialog_sheet)
        val alphabet = dialog.findViewById<RadioButton>(R.id.filter_alpabet)
        val birthday = dialog.findViewById<RadioButton>(R.id.filter_birthday)

        alphabet.setOnClickListener {
            var users = viewModel.recyclerListData.value?.items?.sortedBy { it.firstName }
            var temp = viewModel.recyclerListData.value

            if (temp != null) {
                if (users != null) {
                    temp.items = users
                }
            }
            viewModel.recyclerListData.postValue(temp)
        }
        birthday.setOnClickListener {
            var users = viewModel.recyclerListData.value?.items?.sortedBy { it.birthday }
            var temp = viewModel.recyclerListData.value

            if (temp != null) {
                if (users != null) {
                    temp.items = users
                }
            }
            viewModel.recyclerListData.postValue(temp)
        }
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}