package guru.stefma.cleancomponents.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import guru.stefma.cleancomponents.sample.usecase.AwesomeSingleMore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var awesome = true as AwesomeSingleMore
    }
}
