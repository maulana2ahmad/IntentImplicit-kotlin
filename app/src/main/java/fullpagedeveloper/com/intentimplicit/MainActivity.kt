package fullpagedeveloper.com.intentimplicit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fullpagedeveloper.com.intentimplicit.MoveForResultActivity.Companion.EXTRA_SELECTED_VALUE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moveForResult.setOnClickListener {
            val moveForResult = Intent(this, MoveForResultActivity::class.java)
            startActivityForResult(moveForResult,REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE){
                val selectedValue = data?.getIntExtra(EXTRA_SELECTED_VALUE, 0)
                textViewResult.text = "Hasil ${selectedValue}"
            }
        }
    }
}
