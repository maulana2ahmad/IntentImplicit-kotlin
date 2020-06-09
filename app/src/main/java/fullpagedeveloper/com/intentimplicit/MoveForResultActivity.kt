package fullpagedeveloper.com.intentimplicit

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_move_for_result.*


class MoveForResultActivity : AppCompatActivity() {

    var resultIntent = Intent()

    companion object {
        var EXTRA_SELECTED_VALUE = "extra_selected_value"
        var RESULT_CODE = 110
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        radioGroupNumber.setOnCheckedChangeListener { group, checkedId ->
            var radio : RadioButton = findViewById(checkedId)
        }

        buttonChoose.setOnClickListener {
            val id: Int = radioGroupNumber.checkedRadioButtonId
            if (id != 0) {
                var value = 0
                when(id) {
                    R.id.radioButton_50 -> value = 50
                    R.id.radioButton_100 -> value = 100
                    R.id.radioButton_150 -> value = 150
                    R.id.radioButton_200 -> value = 200
                }

                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value)
                setResult(RESULT_CODE, resultIntent)
                finish()
            }
        }

    }
}
