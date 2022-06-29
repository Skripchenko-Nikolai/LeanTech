package com.pirksni.leantech.presentation.screen.menu

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pirksni.leantech.R
import com.pirksni.leantech.databinding.FragmentMenuBinding
import com.pirksni.leantech.extensions.updateBottomPaddingEdgeToEdge
import com.pirksni.leantech.presentation.base.BaseFragment

class MenuFragment : BaseFragment<MenuViewModel>(R.layout.fragment_menu) {

    private val binding by viewBinding(FragmentMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            bottomNavigation.updateBottomPaddingEdgeToEdge()
            initBottomNavigationView()
        }
    }


    private fun initBottomNavigationView() {

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.menuContainer) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }
}
