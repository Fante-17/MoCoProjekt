package stella.deborah.bulletjournaling.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import stella.deborah.bulletjournaling.R
import stella.deborah.bulletjournaling.model.Event

class ItemAdapter(private val context: Context,
                  private val dataset: List<Event>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.item_name)
        val textOrt: TextView = view.findViewById(R.id.item_ort)
        val textStart: TextView = view.findViewById(R.id.item_start)
        val textEnd: TextView = view.findViewById(R.id.item_end)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_event_archives, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val event = dataset[position]
        holder.textName.text =event.eventName
        holder.textOrt.text = event.eventPlace
        holder.textStart.text = "${event.startDay} ${event.startTime}"
        holder.textEnd.text = "${event.endDay} ${event.endTime}"
    }

    override fun getItemCount(): Int {
      return dataset.size
    }
}
