package com.github.lipenathan.teamcreator.views.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.FragmentPlayerRegisterBinding
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.model.Position
import com.github.lipenathan.teamcreator.services.persistence.PlayerMemoryDataBase
import com.github.lipenathan.teamcreator.views.BaseFragment
import com.github.lipenathan.teamcreator.views.PlayerListFragment
import com.github.lipenathan.teamcreator.views.components.ModalBottomSheet
import com.github.lipenathan.teamcreator.views.player.PlayerActivty.Navigation.BACK_STACK
import com.github.lipenathan.teamcreator.views.player.PlayerActivty.Navigation.CONTAINER
import com.google.android.material.snackbar.Snackbar

/**
 * Classe responsável por gerenciar fragment de cadastro de jogador
 */
class PlayerRegisterFragment : BaseFragment(R.layout.fragment_player_register) {

    private val positionsList get() = Position.getAllFlags()
    private val stars: List<ImageView> get() = listOf(binding.star1, binding.star2, binding.star3)
    private var mainPositionName: String? = null
    private var secondPositionName: String? = null
    private var rate: Int = 0
    private val playersDB = PlayerMemoryDataBase()
    private lateinit var binding: FragmentPlayerRegisterBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlayerRegisterBinding.inflate(inflater, container, false)
        setListers()
        return binding.root
    }

    private fun setListers() {
        binding.apply {
            editMainPosition.setOnClickListener {
                val modalBottomSheet = ModalBottomSheet(positionsList) {
                    mainPositionName = it
                    editMainPosition.setText(mainPositionName)
                }
                modalBottomSheet.show(requireActivity().supportFragmentManager, ModalBottomSheet.TAG)
            }
            editSecondPosition.setOnClickListener {
                val modalBottomSheet = ModalBottomSheet(positionsList) {
                    secondPositionName = it
                    editSecondPosition.setText(secondPositionName)
                }
                modalBottomSheet.show(requireActivity().supportFragmentManager, ModalBottomSheet.TAG)
            }
            star1.setOnClickListener {
                changeStarRate(1)
                rate = 1
            }
            star2.setOnClickListener {
                changeStarRate(2)
                rate = 2
            }
            star3.setOnClickListener {
                changeStarRate(3)
                rate = 3
            }
            buttonNextStep.setOnClickListener {
                if (binding.editName.text.isNullOrEmpty()) {
                    Snackbar.make(binding.root, "Nome do jogador é obrigatório", Snackbar.LENGTH_LONG).show()
                } else if (mainPositionName.isNullOrEmpty()) {
                    Snackbar.make(binding.root, "Posição principal do jogador é orbigatória", Snackbar.LENGTH_LONG).show()
                } else if (secondPositionName.isNullOrEmpty()) {
                    Snackbar.make(binding.root, "Poição secundária do jogaador é obrigatória", Snackbar.LENGTH_LONG).show()
                } else if (rate == 0) {
                    Snackbar.make(binding.root, "Estrelas do jogador é obrigatório", Snackbar.LENGTH_LONG).show()
                } else {
                    val mainPosition = Position.getPositionByName(mainPositionName!!)
                    val secondPosition = Position.getPositionByName(secondPositionName!!)
                    val player = Player(editName.text.toString(), mainPosition, secondPosition, rate)
                    playersDB.save(player)
                    navigateReplacing(CONTAINER, PlayerListFragment(), BACK_STACK)
                }
            }
        }
    }

    fun changeStarRate(rate: Int) {
        for (i in 0..rate - 1) {
            stars[i].setImageDrawable(requireContext().getDrawable(R.drawable.star))
        }
        for (i in rate..2) {
            stars[i].setImageDrawable(requireContext().getDrawable(R.drawable.star_empty))
        }
    }
}