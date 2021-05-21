package ch.leytto.cynoclient.ui.dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.viewmodel.ViewModelFactory

class DogsFragment : Fragment() {

    private val homeViewModel: DogViewModel by viewModels {
        ViewModelFactory((activity?.application as CynoClientApplication).dogRepository)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_clients, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.clients_recycler)
        val adapter = DogListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        homeViewModel.allDogs.observe(viewLifecycleOwner, Observer { dogs ->
            dogs?.let { adapter.submitList(it) }
        })

        return root
    }
}