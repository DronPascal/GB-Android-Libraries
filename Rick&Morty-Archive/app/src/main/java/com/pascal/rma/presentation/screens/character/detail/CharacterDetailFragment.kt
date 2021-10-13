package com.pascal.rma.presentation.screens.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import com.pascal.rma.App
import com.pascal.rma.databinding.FragmentCharacterDetailBinding
import com.pascal.rma.domain.model.Character
import com.pascal.rma.presentation.activity.MainActivity
import com.pascal.rma.presentation.image.IImageLoader
import com.pascal.rma.presentation.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class CharacterDetailFragment : MvpAppCompatFragment(), CharacterDetailView, BackButtonListener {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private val presenter by moxyPresenter {
        CharacterDetailPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var binding: FragmentCharacterDetailBinding? = null

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCharacterDetailBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun initData() {
        presenter.character = requireArguments().getParcelable(CHARACTER_ARGUMENT)
    }

    override fun initView() {
        presenter.character?.run {
            activity?.title = ""
            (activity as MainActivity).setDisplayHomeAsUpEnabled(true)

            binding?.run {
                imageLoader.loadTo(image, imgAvatar as ImageView)
                tvName.text = name
                tvStatus.text = status
                tvSpecies.text = species
                tvType.text = type
                tvGender.text = gender
                tvOrigin.text = origin
                tvLocation.text = location
                tvFirstSeen.text = ""
                tvEpisodes.text = ""

                if (status.isBlank()) {
                    tvStatus.visibility = GONE; tvStatusTitle.visibility = GONE
                }
                if (species.isBlank()) {
                    tvSpecies.visibility = GONE; tvSpeciesTitle.visibility = GONE
                }
                if (type.isBlank()) {
                    tvType.visibility = GONE; tvTypeTitle.visibility = GONE
                }
                if (gender.isBlank()) {
                    tvGender.visibility = GONE; tvGenderTitle.visibility = GONE
                }
                if (origin.isBlank()) {
                    tvOrigin.visibility = GONE; tvOriginTitle.visibility = GONE
                }
                if (location.isBlank()) {
                    tvLocation.visibility = GONE; tvLocationTitle.visibility = GONE
                }
                if ("".isBlank()) {
                    tvFirstSeen.visibility = GONE; tvFirstSeenTitle.visibility = GONE
                }
                if ("".isBlank()) {
                    tvEpisodes.visibility = GONE; tvEpisodesTitle.visibility = GONE
                }
            }
        }
    }

    override fun backPressed(): Boolean {
        return presenter.onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance(character: Character): CharacterDetailFragment {
            return CharacterDetailFragment().apply {
                arguments = bundleOf(CHARACTER_ARGUMENT to character)
            }
        }

        private const val CHARACTER_ARGUMENT = "CHARACTER_KEY"
    }

}