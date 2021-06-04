package ch.leytto.cynoclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.viewModels
import ch.leytto.cynoclient.db.CynoClientRoomDatabase
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.viewmodel.ClientViewModel
import ch.leytto.cynoclient.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.util.logging.Logger

class CreateClientActivity : AppCompatActivity() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { CynoClientRoomDatabase.getDatabase(this,applicationScope) }
    val viewModel: ClientViewModel by viewModels {
        ViewModelFactory((application as CynoClientApplication).clientRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_client)
        var sexe: Boolean
        sexe = true
        var et_firstName = findViewById<EditText>(R.id.editFirstname)
        var et_lastName = findViewById<EditText>(R.id.editLastname)
        var et_email = findViewById<EditText>(R.id.editEmail)
        var et_street = findViewById<EditText>(R.id.editStreet)
        var et_locality = findViewById<EditText>(R.id.editLocality)
        var et_phone = findViewById<EditText>(R.id.editPhone)
        var rb_sexe = findViewById<RadioGroup>(R.id.rbGroupSex)
        var btn_submit = findViewById<Button>(R.id.submitForm)
        rb_sexe.setOnCheckedChangeListener{group: RadioGroup?, checkedId: Int ->
            if (checkedId === R.id.rbFemale) {
                sexe = false
            }else if (checkedId === R.id.rbMale){
                sexe = true
            }else{
                sexe = false
            }

        }
        btn_submit.setOnClickListener {
            val firstname = et_firstName.text.toString()
            val lastname = et_lastName.text.toString()
            val email = et_email.text.toString()
            var street = et_street.text.toString()
            var locality = et_locality.text.toString()
            var phone = et_phone.text.toString()
            viewModel.insertClient(Client(0,firstname,lastname,sexe,email,phone,street,locality.toInt()))
            Toast.makeText(this@CreateClientActivity, "L'utilisateur "+et_firstName.text +" "+ et_lastName.text+" a bien été créé.",Toast.LENGTH_LONG).show()
        }
    }

}