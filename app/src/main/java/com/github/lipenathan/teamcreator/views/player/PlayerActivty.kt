package com.github.lipenathan.teamcreator.views.player

import android.os.Bundle
import android.window.OnBackInvokedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.ActivityPlayerRegisterBinding
import com.github.lipenathan.teamcreator.views.PlayerListFragment
import com.github.lipenathan.teamcreator.views.player.PlayerActivty.Navigation.BACK_STACK
import com.github.lipenathan.teamcreator.views.player.PlayerActivty.Navigation.CONTAINER

/**
 * Atividade responsável por gerenciar telas de cadastro de jogadores
 * @author fnanjos
 */
class PlayerActivty: AppCompatActivity() {

    private lateinit var binding: ActivityPlayerRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerRegisterBinding.inflate(layoutInflater)
        window.statusBarColor = getColor(R.color.teal_200)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(CONTAINER, PlayerListFragment(), BACK_STACK)
            .commit()
    }

    object Navigation {
        const val CONTAINER = R.id.container_player_register
        const val BACK_STACK = "player_register_back_stack"
    }
}