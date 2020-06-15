package org.buffer.android.components.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.buffer.android.components.BottomSheetFactory

class MainActivity : AppCompatActivity() {
    lateinit var reminderBottomSheet: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        click_me_button.setOnClickListener {
            reminderBottomSheet = BottomSheetFactory.createSimpleAlertStyleBottomSheet(this,
                    R.string.title_ig_reminder,
                    R.string.subtitle_ig_reminder,
                    R.string.primary_action_ig_reminder_sheet,
                    R.string.secondary_action_ig_reminder_sheet,
                    R.drawable.ig_reminder,
                    View.OnClickListener {
                        reminderBottomSheet.hide()
                    },
                    View.OnClickListener {
                        reminderBottomSheet.hide()
                    })

            reminderBottomSheet.show()
        }
    }
}
