package com.example.newappdi.api_calling.DI

import com.example.newappdi.api_calling.DI.Network.APICallServices
import com.example.newappdi.api_calling.DI.Network.ErrorResponse
import com.example.newappdi.api_calling.DI.Network.Response
import com.example.newappdi.api_calling.DI.Network.SuccessResponse
import com.example.newappdi.api_calling.model.AppMainData
import javax.inject.Inject


class APIRespository @Inject constructor(@BaseUrl2Retrofit val apiCallServices: APICallServices) {

    suspend fun getData(): Response<AppMainData> {
        return try {
            val response = apiCallServices.getData()
            SuccessResponse(response)
        } catch (ee: Exception) {
            ErrorResponse("", ee)
        }
    }
/*
* I have read the content of the page "Kotlin's Flow, ChannelFlow, and CallbackFlow Made Easy" by Elye on Medium. Here's a summary of the key points:

**The article explains the differences and use cases for three Kotlin flow types:**

1. **Flow**: The basic reactive stream for asynchronous data processing. Best for linear data streams with operations like mapping, filtering, etc.
2. **ChannelFlow**: A flow with a buffer, allowing concurrent data emission and reception. Ideal for situations where data arrives faster than it's processed.
3. **CallbackFlow**: A flow built from callbacks, useful when you need to push data from outside the flow's scope. Offers more flexibility but can be less predictable.

**The article highlights the following benefits of using these flow types:**

* Improved code readability and maintainability compared to traditional callbacks.
* Easier handling of asynchronous data processing and cancellation.
* Increased flexibility and composability for building complex data pipelines.

**The author provides practical examples and code snippets for each flow type, making the concepts easy to understand.** They also discuss common pitfalls and best practices for using these flows effectively.

Overall, the article provides a valuable resource for developers who want to learn more about Kotlin flows and how to leverage them effectively in their projects.

**Do you have any specific questions about the content or want me to delve deeper into any particular aspects of the article?**

I'm happy to assist you further!

* */

}