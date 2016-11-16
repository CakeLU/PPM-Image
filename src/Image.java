/**
 * Created by ss2sa on 11/15/2016.
 */
public abstract class Image {
        // ---------------------
        // Image attributes
        // ---------------------
        protected String magic;
        protected int width;
        protected int height;
        protected int depth;

        // Accessors: getMagic(), getWidth(), etc...

        // Modifiers: setMagic(string m), setWidth(int w), etc...

        // -------------------
        // abstract functions
        // -------------------
        public abstract void flip_horizontally();
        public abstract void flip_vertically();
        public abstract void rotate_right_90();
    }

