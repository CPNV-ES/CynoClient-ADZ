package ch.leytto.cynoclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ch.leytto.cynoclient.viewmodel.ClientViewModel
import ch.leytto.cynoclient.viewmodel.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientInfoActivity : AppCompatActivity() {
    private val clientViewModel: ClientViewModel by viewModels {
        ViewModelFactory((this.application as CynoClientApplication).clientRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_info)

        val ref = this
        lifecycleScope.launch(Dispatchers.IO) {
            val clientData = clientViewModel.getById(intent.getStringExtra("client_id")!!)

            ref.runOnUiThread {
                title = "Détails de ${clientData.client.firstname} ${clientData.client.lastname}"
                
                // Firstname
                findViewById<TextView>(R.id.firstname).text = clientData.client.firstname

                // Lastname
                findViewById<TextView>(R.id.lastname).text = clientData.client.lastname

                // Email
                findViewById<TextView>(R.id.email).text = clientData.client.email ?: "Non précisé"

                // Phone
                findViewById<TextView>(R.id.phone).text = clientData.client.phone

                // Street
                findViewById<TextView>(R.id.street).text = clientData.client.street ?: "Non précisée"

                // Locality
                findViewById<TextView>(R.id.locality).text = clientData.locality?.noun ?: "Non précisée"

                // Gender
                if (clientData.client.female)
                    findViewById<TextView>(R.id.gender).text = "Femme"
                else
                    findViewById<TextView>(R.id.gender).text = "Homme"
            }
        }
    }
}