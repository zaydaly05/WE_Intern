import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class Post {
  final String user;
  final String time;
  final String message;
  final int likes;
  final int comments;

  Post({
    required this.user,
    required this.time,
    required this.message,
    required this.likes,
    required this.comments,
  });
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: DashboardScreen(),
    );
  }
}

class DashboardScreen extends StatelessWidget {
  DashboardScreen({super.key});

  final List<Post> posts = [
    Post(
      user: "John Smith",
      time: "2 min ago",
      message: "Learning Jetpack Compose today!",
      likes: 120,
      comments: 15,
    ),
    Post(
      user: "Emma",
      time: "10 min ago",
      message: "Flutter is awesome ❤️",
      likes: 88,
      comments: 9,
    ),
    Post(
      user: "Michael",
      time: "20 min ago",
      message: "Compose feels very declarative.",
      likes: 150,
      comments: 32,
    ),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Dashboard"),
      ),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [

          Container(
            padding: const EdgeInsets.all(20),
            decoration: BoxDecoration(
              color: Colors.blue,
              borderRadius: BorderRadius.circular(16),
            ),
            child: const Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  "Welcome Back",
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 26,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                SizedBox(height: 8),
                Text(
                  "You have 18 new notifications",
                  style: TextStyle(color: Colors.white70),
                ),
              ],
            ),
          ),

          const SizedBox(height: 20),

          const Text(
            "Recent Posts",
            style: TextStyle(
              fontSize: 22,
              fontWeight: FontWeight.bold,
            ),
          ),

          const SizedBox(height: 15),

          ...posts.map((post) {
            return Card(
              margin: const EdgeInsets.only(bottom: 16),
              elevation: 3,
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(14),
              ),
              child: Padding(
                padding: const EdgeInsets.all(16),
                child: Column(
                  children: [

                    Row(
                      children: [
                        const CircleAvatar(
                          radius: 24,
                          child: Icon(Icons.person),
                        ),
                        const SizedBox(width: 12),

                        Expanded(
                          child: Column(
                            crossAxisAlignment:
                                CrossAxisAlignment.start,
                            children: [
                              Text(
                                post.user,
                                style: const TextStyle(
                                  fontWeight: FontWeight.bold,
                                  fontSize: 18,
                                ),
                              ),
                              Text(post.time),
                            ],
                          ),
                        ),

                        const Icon(Icons.more_vert),
                      ],
                    ),

                    const SizedBox(height: 15),

                    Align(
                      alignment: Alignment.centerLeft,
                      child: Text(
                        post.message,
                        style: const TextStyle(fontSize: 16),
                      ),
                    ),

                    const SizedBox(height: 15),

                    Row(
                      mainAxisAlignment:
                          MainAxisAlignment.spaceAround,
                      children: [
                        Row(
                          children: [
                            const Icon(Icons.favorite,
                                color: Colors.red),
                            const SizedBox(width: 6),
                            Text("${post.likes}"),
                          ],
                        ),
                        Row(
                          children: [
                            const Icon(Icons.comment),
                            const SizedBox(width: 6),
                            Text("${post.comments}"),
                          ],
                        ),
                        const Icon(Icons.share),
                      ],
                    ),
                  ],
                ),
              ),
            );
          }),
        ],
      ),
    );
  }
}