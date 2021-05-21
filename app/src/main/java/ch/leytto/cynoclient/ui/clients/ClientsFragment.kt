package ch.leytto.cynoclient.ui.clients

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.UserInfoActivity
import ch.leytto.cynoclient.ui.dogs.ClientListAdapter
import ch.leytto.cynoclient.viewmodel.ClientViewModel
import ch.leytto.cynoclient.viewmodel.ViewModelFactory

class ClientsFragment : Fragment() {

    private val clientViewModel: ClientViewModel by viewModels {
        ViewModelFactory((activity?.application as CynoClientApplication).clientRepository)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_clients, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.clients_recycler)
        val adapter = ClientListAdapter { client ->
            val intent = Intent(context, UserInfoActivity::class.java).apply {
                putExtra("client_id", client.id.toString())
            }
            startActivity(intent)

        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        clientViewModel.allClients.observe(viewLifecycleOwner, Observer { client ->
            client?.let { adapter.submitList(it) }
        })

        return root
    }
}