# IntentImplicit-kotlin

![untitled](https://user-images.githubusercontent.com/43386555/84180131-372f4700-aab1-11ea-8212-de2bea12c8b0.gif)

1. buat class baru dengan nama MoveForResultActivity
2. Selanjutnya buat layout radioButtonGroup dan chaild


        <?xml version="1.0" encoding="utf-8"?>
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            xmlns:tools="http://schemas.android.com/tools"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            tools:context=".MoveForResultActivity">

            <TextView
                android:text="Pilih angka yang kamu suka"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>

            <RadioGroup
                android:id="@+id/radioGroupNumber"
                android:layout_width="match_parent"
                android:layout_height="wrap_content">

                <RadioButton
                    android:id="@+id/radioButton_50"
                    android:text="50"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"/>

                <RadioButton
                    android:id="@+id/radioButton_100"
                    android:text="100"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"/>

                <RadioButton
                    android:id="@+id/radioButton_150"
                    android:text="150"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"/>

                <RadioButton
                    android:id="@+id/radioButton_200"
                    android:text="200"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"/>

            </RadioGroup>

            <Button
                android:id="@+id/buttonChoose"
                android:text="Pilih"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>

        </LinearLayout>
        
        
3. buat variable static yang dapat di akses class lain


          companion object {
                  var EXTRA_SELECTED_VALUE = "extra_selected_value"
                  var RESULT_CODE = 110
              }
              
4. Selanjutnya buat object intent

        var resultIntent = Intent()
        
5. buat button listener dan memberi validasi apakah nilai object radiabutton yang dipilih, bila ada maka proses selanjutnya menentukan obyek radioButton mana yang di klik berdasarkan nilai dari radioGroupNumber.checkedRadioButtonId 


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
                    
                    
6. Selanjutnya kita membuat intent tanpa input apapun di construktor nya kemudian kita meletakan variable value ke dalam metode putExtra(key, value) dengan EXTRA_SELECTED_VALUE bertipe static string dan bernilai "extra_selected_value". Kemudian kita jadikan obyek resultIntent yang telah dibuat sebelumnya menjadi parameter dari setResult(RESULT_CODE, Intent) setelah itu kita panggil method finish() untuk menutup class MoveForResultActivity

                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value)
                                            setResult(RESULT_CODE, resultIntent)
                                            finish()
                                            
7. Setelah MoveForResultActivitty telah tertutup sempurna maka metode onActivityForResult() pada MainActivitu akan dijalankan


        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                super.onActivityResult(requestCode, resultCode, data)
                if (requestCode == REQUEST_CODE) {
                    if (resultCode == MoveForResultActivity.RESULT_CODE){
                        val selectedValue = data?.getIntExtra(EXTRA_SELECTED_VALUE, 0)
                        textViewResult.text = "Hasil ${selectedValue}"
                    }
                }
            }
            
8. Sebelumnya kita akan panggil dulu id dari button movetoresult yang ada di class MainActivity


        moveForResult.setOnClickListener {
                          val moveForResult = Intent(this, MoveForResultActivity::class.java)
                          startActivityForResult(moveForResult,REQUEST_CODE)
                      }
                      
                      
## xml MainCtivity

              <?xml version="1.0" encoding="utf-8"?>
              <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                  xmlns:tools="http://schemas.android.com/tools"
                  android:layout_width="match_parent"
                  android:layout_height="match_parent"
                  android:gravity="center"
                  android:orientation="vertical"
                  tools:context=".MainActivity">

                  <Button
                      android:id="@+id/moveForResult"
                      android:layout_width="match_parent"
                      android:layout_height="wrap_content"
                      android:text="Pindah Activity untuk Result" />

                  <TextView
                      android:id="@+id/textViewResult"
                      android:layout_width="match_parent"
                      android:layout_height="wrap_content"
                      android:gravity="center"
                      android:text="Result"
                      android:textSize="50sp" />

              </LinearLayout>
