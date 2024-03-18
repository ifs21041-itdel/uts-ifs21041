import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21041.dinopedia.DetailActivity
import com.ifs21041.dinopedia.Dino
import com.ifs21041.dinopedia.R
import com.ifs21041.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataDinos = ArrayList<Dino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDinos.setHasFixedSize(false)
        dataDinos.addAll(getListDinos())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDinos(): ArrayList<Dino> {
        val dataName =
            resources.getStringArray(R.array.dino_famili)
        val dataIcon =
            resources.obtainTypedArray(R.array.dino_icon)
        val dataDescription =
            resources.getStringArray(R.array.dino_desc)
        val dataCharacteristic =
            resources.getStringArray(R.array.dino_characteristic)
        val dataHabitat =
            resources.getStringArray(R.array.dino_habitat)
        val dataEnvironment =
            resources.getStringArray(R.array.dino_lingkungan)
        val dataBehavior =
            resources.getStringArray(R.array.dino_perilaku)
        val dataClassification =
            resources.getStringArray(R.array.dino_klasifikasi)

        val listDino = ArrayList<Dino>()
        for (i in dataName.indices) {
            val dino = Dino(dataName[i],
                dataIcon.getResourceId(i, -1), dataDescription[i],
                dataCharacteristic[i], dataHabitat[i], dataEnvironment[i],
                dataBehavior[i], dataClassification[i])
            listDino.add(dino)
        }
        return listDino
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDinos.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDinos.layoutManager =
                LinearLayoutManager(this)
        }

        val listDinoAdapter = ListDinoAdapter(dataDinos)
        binding.rvDinos.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object :
            ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }
}
