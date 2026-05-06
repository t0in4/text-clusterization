create in root of project file .env  
and add gigachat key GIGACHAT_AUTH_KEY="<some key>"  
application reads news from a file and categorizes it according to  
its title, such as music, sports, or any category that the embedding model thinks is  
related to the same topic.  
We’ll use the implementation of the DBSCAN algorithm provided by the Apache  
Commons Math library to cluster the topic of the news, and the OpenAI model to  
automatically label each of the clusters with a meaningful name based on the news  
placed in the cluster.  
