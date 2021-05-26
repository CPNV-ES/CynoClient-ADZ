package ch.leytto.cynoclient.ui.report

import android.R
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.stepstone.stepper.Step
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter
import com.stepstone.stepper.viewmodel.StepViewModel

val CURRENT_STEP_POSITION_KEY = "CURRENT_STEP_POSITION_KEY"

class ReportStepperAdapter(context: Context, fm: FragmentManager) : AbstractFragmentStepAdapter(fm, context) {

    override fun createStep(position: Int): Step? {
        val step = CreateReportDefaultFragment()
        val b = Bundle()
        b.putInt(CURRENT_STEP_POSITION_KEY, position)
        step.arguments = b
        return step
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getViewModel( position: Int): StepViewModel {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        return StepViewModel.Builder(context)
            .setTitle("Titre") //can be a CharSequence instead
            .create()
    }
}