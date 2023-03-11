package com.github.lipenathan.teamcreator.views

import androidx.fragment.app.Fragment

open class BaseFragment(id: Int) : Fragment(id) {

    protected fun navigateReplacing(container: Int, fragment: Fragment, backStack: String? = null, removeFragment: Fragment? = null) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.addToBackStack(backStack).replace(container, fragment)
        if (removeFragment != null) {
            transaction.remove(removeFragment)
        }
        transaction.commit()
    }

    protected fun navigateAdding(container: Int, fragment: Fragment, backStack: String? = null, removeFragment: Fragment? = null) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.addToBackStack(backStack).add(container, fragment)
        if (removeFragment != null) {
            transaction.remove(removeFragment)
        }
        transaction.commit()
    }
}