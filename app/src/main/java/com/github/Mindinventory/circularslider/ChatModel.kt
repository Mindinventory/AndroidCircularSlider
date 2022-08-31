package com.github.Mindinventory.circularslider

data class ChatModel(
    val userType: Int,
    val chatImage: Int,
    val chatText: String,
)

fun populateChatList(): ArrayList<ChatModel> {
    val chatContentList = arrayListOf<ChatModel>()
    return chatContentList.apply {
        add(
            ChatModel(
                userType = 0,
                chatImage = R.drawable.teamwork,
                chatText = "Planning and requirement analysis"
            )
        )
        add(ChatModel(userType = 1, chatImage = R.drawable.work, chatText = "Defining requirement"))
        add(
            ChatModel(
                userType = 0,
                chatImage = R.drawable.computer,
                chatText = "Designing and product architecture"
            )
        )
        add(
            ChatModel(
                userType = 1,
                chatImage = R.drawable.responsive,
                chatText = "Building or developing the product"
            )
        )
        add(
            ChatModel(
                userType = 0,
                chatImage = R.drawable.report,
                chatText = "Testing activities and the product"
            )
        )
        add(
            ChatModel(
                userType = 1,
                chatImage = R.drawable.work,
                chatText = "Deployment in the market and Maintenance"
            )
        )
    }
}