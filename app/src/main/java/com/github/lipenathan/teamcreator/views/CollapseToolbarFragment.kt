package com.github.lipenathan.teamcreator.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.FragmentCollapseToolbarBinding

class CollapseToolbarFragment : BaseFragment(R.layout.fragment_collapse_toolbar) {

    private lateinit var binding: FragmentCollapseToolbarBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCollapseToolbarBinding.inflate(inflater, container, false)
        return binding.root
    }
}