package com.aires.cloud.common.utils;


import com.aires.cloud.common.entity.router.VueRouter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aires
 */
public class TreeUtil {

    private final static String TOP_NODE_ID = "0";

    /**
     * 构造前端路由
     *
     * @param routes routes
     * @param <T>    T
     * @return ArrayList<VueRouter < T>>
     */
    public static <T> List<VueRouter<T>> buildVueRouter(List<VueRouter<T>> routes) {
        if (routes == null) {
            return null;
        }
        List<VueRouter<T>> topRoutes = new ArrayList<>();
        VueRouter<T> router = new VueRouter<>();
        for (VueRouter<T> route : routes) {
            String parentId = route.getParentId();
            if (parentId == null || TOP_NODE_ID.equals(parentId)) {
                topRoutes.add(route);
                continue;
            }
            for (VueRouter<T> parent : routes) {
                String id = parent.getId();
                if (id != null && id.equals(parentId)) {
                    if (parent.getChildren() == null) {
                        parent.initChildren();
                    }
                    parent.getChildren().add(route);
                    parent.setAlwaysShow(true);
                    parent.setHasChildren(true);
                    route.setHasParent(true);
                    parent.setHasParent(true);
                    continue;
                }
            }
        }
        VueRouter<T> router404 = new VueRouter<>();
        router404.setName("404");
        router404.setComponent("error-page/404");
        router404.setPath("*");

        topRoutes.add(router404);
        return topRoutes;
    }

}