import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21041.dinopedia.Dino
import com.ifs21041.dinopedia.databinding.ItemRowDinoBinding

class ListDinoAdapter(private val listDinos: ArrayList<Dino>) :
    RecyclerView.Adapter<ListDinoAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dino = listDinos[position]
        holder.binding.ivItemDino.setImageResource(dino.icon)
        holder.binding.tvItemDino.text = dino.name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDinos[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDinos.size

    class ListViewHolder(val binding: ItemRowDinoBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Dino)
    }
}
