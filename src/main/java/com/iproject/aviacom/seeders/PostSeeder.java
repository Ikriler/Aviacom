package com.iproject.aviacom.seeders;

import com.iproject.aviacom.models.Post;
import com.iproject.aviacom.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostSeeder {

    private static List<Post> postList = new ArrayList<>();

    private static void init() {
        postList.add(new Post("ADMIN", "Админ"));
        postList.add(new Post("CASHIER", "Кассир"));
        postList.add(new Post("AIRDROME", "Диспетчер"));
        postList.add(new Post("BOOKING", "Агент бронирования"));
        postList.add(new Post("PERSONNEL", "Кадровик"));
    }

    public static void seed(PostRepository postRepository) {
        init();
        for (Post post: postList) {
            if(postRepository.findByName(post.getName()) == null) {
                postRepository.save(post);
            }
        }
    }
}
