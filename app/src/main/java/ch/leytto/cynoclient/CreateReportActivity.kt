package ch.leytto.cynoclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ch.leytto.cynoclient.ui.report.ReportStepperAdapter
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError

class CreateReportActivity : AppCompatActivity(), StepperLayout.StepperListener {

    // TODO: Move currentStep into ViewModel as onCreate is called when the phone is rotated for example...
    var currentStep: Int = 0
    lateinit var stepperLayout: StepperLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_report)

        stepperLayout = findViewById(R.id.stepper_layout)
        stepperLayout.adapter = ReportStepperAdapter(this, supportFragmentManager)
        stepperLayout.setListener(this)
    }

    override fun onResume() {
        super.onResume()
        stepperLayout.currentStepPosition = currentStep
    }

    override fun onRestart() {
        super.onRestart()
        stepperLayout.currentStepPosition = currentStep
    }

    override fun onCompleted(completeButton: View?) {
    }

    override fun onError(verificationError: VerificationError?) {
    }

    override fun onStepSelected(newStepPosition: Int) {
        currentStep = newStepPosition
    }

    override fun onReturn() {
    }
}