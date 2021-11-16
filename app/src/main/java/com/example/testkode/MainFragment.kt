package com.example.testkode

    import android.annotation.SuppressLint
    import android.app.Dialog
    import android.graphics.Color
    import android.graphics.drawable.ColorDrawable
    import android.os.Bundle
    import android.view.*
    import android.widget.Button
    import android.widget.ImageButton
    import android.widget.ImageView
    import android.widget.RadioButton
    import androidx.appcompat.widget.SearchView
    import androidx.fragment.app.Fragment
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModelProvider
    import androidx.viewpager2.widget.ViewPager2
    import com.example.testkode.adapter.ListAdapter
    import com.example.testkode.models.User
    import com.example.testkode.models.UserList
    import com.example.testkode.viewModel.MainViewModel
    import com.google.android.material.tabs.TabLayout
    import com.google.android.material.tabs.TabLayoutMediator


class MainFragment() : Fragment() {

    private var searchUserList: ArrayList<User> = arrayListOf()
    lateinit var viewModel: MainViewModel
    private var allUsers: ArrayList<User> = arrayListOf()
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

        filter.setOnClickListener {
            showDialog()
        }

        initViewModel()

        if (error){
            view = inflater.inflate(R.layout.error, container, false)
            val back = view.findViewById<Button>(R.id.back)
            back.setOnClickListener { view = inflater.inflate(R.layout.fragment_analytics, container, false) }
        }

        //SearchView
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

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
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


    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getUserList().observe(viewLifecycleOwner, {
            if (it != null){

            }else{
                error = true
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

}