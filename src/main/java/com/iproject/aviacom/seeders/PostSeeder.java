package com.iproject.aviacom.seeders;

import com.iproject.aviacom.models.Post;
import com.iproject.aviacom.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostSeeder {

    private static List<Post> postList = new ArrayList<>();

    private static void init() {
        postList.add(new Post("ADMIN"));
        postList.add(new Post("CASHIER"));
        postList.add(new Post("AIRDROME"));
        postList.add(new Post("BOOKING"));
        postList.add(new Post("PERSONNEL"));
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
