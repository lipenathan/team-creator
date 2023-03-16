package com.github.lipenathan.teamcreator.views.player

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.github.lipenathan.teamcreator.R
import com.github.lipenathan.teamcreator.databinding.FragmentPlayerRegisterBinding
import com.github.lipenathan.teamcreator.model.Player
import com.github.lipenathan.teamcreator.model.Position
import com.github.lipenathan.teamcreator.services.persistence.local.AppDataBase
import com.github.lipenathan.teamcreator.viewmodel.PlayerRegisterViewModel
import com.github.lipenathan.teamcreator.views.BaseFragment
import com.github.lipenathan.teamcreator.views.components.ModalBottomSheet
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Classe responsável por gerenciar fragment de cadastro de jogador
 */
class PlayerRegisterFragment : BaseFragment(R.layout.fragment_player_register) {

    private val positionsList get() = Position.getAllFlags()
    private val stars: List<ImageView> get() = listOf(binding.star1, binding.star2, binding.star3)
    private var mainPositionName: String? = null
    private var secondPositionName: String? = null
    private var rate: Int = 0

    //    private val playersDB = PlayerMemoryDataBase()
    private lateinit var db: AppDataBase
    private lateinit var binding: FragmentPlayerRegisterBinding
    private lateinit var viewModel: PlayerRegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(PlayerRegisterViewModel::class.java)
        setObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlayerRegisterBinding.inflate(inflater, container, false)
        db = Room.databaseBuilder(requireContext(), AppDataBase::class.java, "team-creator-database").build()
        setListers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.apply {
            error.observe(this@PlayerRegisterFragment) {
                val dialog = MaterialAlertDialogBuilder(this@PlayerRegisterFragment.requireContext())
                dialog.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                    requireActivity().supportFragmentManager.popBackStack()
                }
                dialog.setMessage(it)
                dialog.show()
            }
            saved.observe(this@PlayerRegisterFragment) {
                Snackbar.make(binding.root, "Jogador salvo com sucesso", Snackbar.LENGTH_LONG).setAction("OK", {
                    requireActivity().supportFragmentManager.popBackStack()
                }).addCallback(object : BaseCallback<Snackbar>() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        requireActivity().supportFragmentManager.popBackStack()
                    }

                    override fun onShown(transientBottomBar: Snackbar?) {
                        super.onShown(transientBottomBar)
                    }
                }).show()
            }
        }
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
            buttonAddPlayer.setOnClickListener {
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
                    viewModel.savePlayer(player)
                }
            }
        }
    }

    private fun savePlayer(player: Player) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default) {
            try {
                val playerDao = db.playerDao()
                playerDao.save(player)
                Snackbar.make(binding.root, "Jogador salvo com sucesso", Snackbar.LENGTH_LONG).show()
            } catch (e: Exception) {
                val dialog = MaterialAlertDialogBuilder(this@PlayerRegisterFragment.requireContext())
                dialog.setMessage(e.message)
                dialog.show()
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