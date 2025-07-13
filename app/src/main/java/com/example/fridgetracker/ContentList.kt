package com.example.fridgetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fridgetracker.databinding.FragmentContentListBinding

class ContentList : Fragment(), ContentItemClickListener {

    private lateinit var binding: FragmentContentListBinding
    private val contentsViewModel: ContentsViewModel by viewModels {
        ContentItemModelFactory((requireActivity().application as FridgeTrackerApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView(){
        val mainActivity = this
        contentsViewModel.contentItems.observe(viewLifecycleOwner){
            binding.contentListRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ContentItemAdapter(it, mainActivity)
            }
        }
    }

    override fun editContentItem(contentItem: ContentItem) {
        NewItemSheet(contentItem).show(requireActivity().supportFragmentManager, "newContentTag")
    }
}