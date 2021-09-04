package com.pascaciorc.checkpoint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pascaciorc.checkpoint.adapter.CheckpointAdapter
import com.pascaciorc.checkpoint.data.Checkpoint
import com.pascaciorc.checkpoint.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkpointRecyclerView.adapter = CheckpointAdapter(
            listOf(
                Checkpoint("Nombre de prueba", "Direccion de prueba", 0L, 0L)
            )
        )
    }
}