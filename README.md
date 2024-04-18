# AndroidAssesment

Here the code i have implemented using 
MVVM architecture and followed Clean architecture and SOLID principles,
used Hilt for DI and for api call used Retrofit with Moshi for response parsing, and
followed Single Activity Architecture using Navigation Graph. 

As per the requirement integrated **post** API with pagination, currently i have implemented paging using RecyclerView ScrollListener, you can see in video,
so for newly added items only i am notifying the adapter using **postListAdapter.notifyItemRangeInserted(lastIndex,postArrayList.size)**, so will not redraws for existing list items
for pagination we can also use Paging library, so using PagedAdapter and PagedDataSource we can implement it, and to optimize list loading in UI we can use **DiffCallback** 
so **DiffCallback** will handle for which item to update and which item to add so will reduce the redraw of all items again and again.

Video Link
https://drive.google.com/file/d/1DCnzdmY1_T-1_uiMWZObGY4M-CyvEhRV/view?usp=sharing