package ch.leytto.cynoclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ch.leytto.cynoclient.ui.report.ReportStepperAdapter
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError

class CreateReportActivity : AppCompatActivity(), StepperLayout.StepperListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_report)

        val stepperLayout = findViewById<StepperLayout>(R.id.stepper_layout)
        stepperLayout.adapter = ReportStepperAdapter(this, supportFragmentManager)
        stepperLayout.setListener(this)
    }

    override fun onCompleted(completeButton: View?) {
    }

    override fun onError(verificationError: VerificationError?) {
    }

    override fun onStepSelected(newStepPosition: Int) {
    }

    override fun onReturn() {
    }
}