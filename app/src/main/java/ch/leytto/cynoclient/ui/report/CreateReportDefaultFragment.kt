package ch.leytto.cynoclient.ui.report

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.viewmodel.ReportViewModel
import ch.leytto.cynoclient.viewmodel.ViewModelFactory
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError

class CreateReportDefaultFragment : Fragment(), Step, SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {

    private val reportViewModel: ReportViewModel by viewModels {
        val app = activity?.application as CynoClientApplication
        ViewModelFactory(app.clientRepository, app.dogRepository)
    }

    private lateinit var clientsAdapter: ArrayAdapter<String>

    private lateinit var clientsSearch: SearchView

    private lateinit var clientsListView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_report_default, container, false)

        // TODO: Use AutoCompleteTextView instead of doing things by hand duuh
        clientsListView = view.findViewById(R.id.clients_listview)
        clientsAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item)
        clientsListView.adapter = clientsAdapter
        clientsListView.onItemClickListener = this
        clientsListView.visibility = View.GONE

        val dogsSpinner = view.findViewById<Spinner>(R.id.dogs_spinner);
        val dogsAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item)
        dogsSpinner.adapter = dogsAdapter

        reportViewModel.allDogs.observe(viewLifecycleOwner, Observer { dogs ->
            dogsAdapter.addAll(dogs.map { dog -> dog.noun })
        })

        reportViewModel.allClients.observe(viewLifecycleOwner, Observer { clients ->
            clientsAdapter.addAll(clients.map {client: Client -> "${client.firstname} ${client.lastname}" })
        })

        clientsSearch = view.findViewById(R.id.clients_searchview)
        clientsSearch.setOnQueryTextListener(this)

        clientsSearch.setOnQueryTextFocusChangeListener { _, hasFocus: Boolean ->
            if (hasFocus) {
                clientsListView.visibility = View.VISIBLE
            } else {
                // We do not want the listview to take any space, we set it to GONE and not INVISIBLE
                clientsListView.visibility = View.GONE
            }
        }

        return view
    }

    override fun verifyStep(): VerificationError? {
        // Return null if the user can go to the next step, create a new VerificationError instance otherwise
        return null;
    }

    override fun onSelected() {
    }

    override fun onError(error: VerificationError) {
        Toast.makeText(context, "Une erreure est survenue", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        // Do not let the default handler close the keyboard when submitting the search query.
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        clientsAdapter.filter.filter(newText)
        clientsListView.visibility = View.VISIBLE

        return true
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val clientName = clientsAdapter.getItem(position)

        clientsSearch.setQuery(clientName, false)

        // Close the keyboard
        val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(clientsSearch.windowToken, InputMethodManager.RESULT_HIDDEN)

        clientsSearch.clearFocus()

    }
}