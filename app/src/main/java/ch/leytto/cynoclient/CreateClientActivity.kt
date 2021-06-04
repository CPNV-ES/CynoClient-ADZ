package ch.leytto.cynoclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import ch.leytto.cynoclient.db.CynoClientRoomDatabase
import ch.leytto.cynoclient.viewmodel.ClientViewModel
import ch.leytto.cynoclient.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CreateClientActivity : AppCompatActivity() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { CynoClientRoomDatabase.getDatabase(this,applicationScope) }
    val viewModel: ClientViewModel by viewModels {
        ViewModelFactory((application as CynoClientApplication).clientRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_client)
        var et_firstName = findViewById<EditText>(R.id.editFirstname)
        var et_lastName = findViewById<EditText>(R.id.editLastname)
        var et_email = findViewById<EditText>(R.id.editEmail)
        var btn_submit = findViewById<Button>(R.id.submitForm)

        btn_submit.setOnClickListener {
            val firstname = et_firstName.text.toString()
            val lastname = et_lastName.text.toString()
            val email = et_email.text.toString()
            //viewModel.insertClient(Client(0,firstname,lastname))
            Toast.makeText(this@CreateClientActivity, "L'utilisateur "+et_firstName.text +" "+ et_lastName.text+" a bien été créé.",Toast.LENGTH_LONG).show()
        }
    }

}