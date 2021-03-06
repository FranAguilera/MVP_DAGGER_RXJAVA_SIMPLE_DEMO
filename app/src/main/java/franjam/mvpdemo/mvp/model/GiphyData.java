package franjam.mvpdemo.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * Generated by copying JSON data response using GsonFormat plugin
 *
 * Check it here: https://plugins.jetbrains.com/plugin/7654-gsonformat
 */
@SuppressWarnings("unused")
public class GiphyData {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public int getSize() {
        if (data == null) return 0;
        else return data.size();
    }

    public String getUrl(int position) {
        String url = "";
        if (data != null && data.size() > position) {
            url = data.get(position).getImages().getFixedHeight().getUrl();
        }
        return url;
    }

    private static class Data {
        private Images images;

        public Images getImages() {
            return images;
        }

        private static class Images {
            @SerializedName("fixed_height")
            private FixedHeight fixedHeight;

            public FixedHeight getFixedHeight() {
                return fixedHeight;
            }

            public static class FixedHeight {
                private String url;

                public String getUrl() {
                    return url;
                }
            }
        }
    }
}
