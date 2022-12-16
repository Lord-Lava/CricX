package com.lava.cricx.ui.component.players.infoFragments

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.lava.cricx.R
import com.lava.cricx.data.Resource
import com.lava.cricx.databinding.FragmentCareerBinding
import com.lava.cricx.domain.model.players.PlayerCareer
import com.lava.cricx.ui.base.BaseFragment
import com.lava.cricx.ui.component.players.PlayerInfoViewModel
import com.lava.cricx.ui.component.players.adapter.career.CareerAdapter
import com.lava.cricx.util.SingleEvent
import com.lava.cricx.util.extensions.gone
import com.lava.cricx.util.extensions.observe
import com.lava.cricx.util.extensions.showToast
import com.lava.cricx.util.extensions.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CareerFragment : BaseFragment<FragmentCareerBinding>(R.layout.fragment_career) {

    private val viewModel: PlayerInfoViewModel by activityViewModels()
    lateinit var careerAdapter: CareerAdapter

    override fun initViewBinding(view: View): FragmentCareerBinding =
        FragmentCareerBinding.bind(view)

    override fun setupViews() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvCareer.layoutManager = layoutManager
    }

    private fun handlePlayerCareer(status: Resource<PlayerCareer>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> bindCareerData(status.data)
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { viewModel.showToastMessage(it) }
            }
        }
    }

    private fun showDataView(show: Boolean) {
        binding.pbLoading.visibility = if (show) View.GONE else View.VISIBLE
        binding.rvCareer.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showLoadingView() {
        binding.rvCareer.gone()
        binding.pbLoading.visible()
    }

    private fun bindCareerData(career: PlayerCareer?) {
        if (!career?.careerList.isNullOrEmpty()) {
            careerAdapter = CareerAdapter(career!!.careerList)
            binding.rvCareer.adapter = careerAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Toast.LENGTH_LONG)
    }

    override fun observeViewModel() {
        observe(liveData = viewModel.playerCareer, ::handlePlayerCareer)
        observeToast(viewModel.showToast)
    }

}