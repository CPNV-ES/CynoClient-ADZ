package ch.leytto.cynoclient.ui.report

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.stepstone.stepper.Step
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter
import com.stepstone.stepper.viewmodel.StepViewModel
import java.lang.IllegalArgumentException

const val CURRENT_STEP_POSITION_KEY = "CURRENT_STEP_POSITION_KEY"

class ReportStepperAdapter(context: Context, fm: FragmentManager) : AbstractFragmentStepAdapter(fm, context) {

    override fun createStep(position: Int): Step {
        val bundle = Bundle()
        bundle.putInt(CURRENT_STEP_POSITION_KEY, position)

        return when (position) {
            0 -> {
                val step = CreateReportDefaultFragment()
                step.arguments = bundle
                step
            }
            1,2 -> {
                val step = CreateReportDefaultFragment()
                step.arguments = bundle
                step
            }
            else -> throw IllegalArgumentException("Out of bound, position : $position")
        }
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