package my.andr.snake;
 
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * TileView: a View-variant designed for handling arrays of "icons" or other drawables.
 */
public class TileView extends View {
    /** 타일 하나의 크기<p/>
     * Parameters controlling the size of the tiles and their range within view.
     * Width/Height are in pixels, and Drawables will be scaled to fit to these
     * dimensions. X/Y Tile Counts are the number of tiles that will be drawn.
     */
    protected static int mTileSize;
    /**화면가로에 들어가는 타일의 갯수*/
    protected static int mXTileCount;
    /**화면세로에 들어가는 타일의 갯수*/
    protected static int mYTileCount;
    /**가로벽 위치*/
    private static int mXOffset;
    /**세로벽 위치*/
    private static int mYOffset;
    /**타일 이미지를 저장하기 위한 비트맵 배열<br/>
     * A hash that maps integer handles specified by the subclasser to the
     * drawable that will be used for that reference
     */
    private Bitmap[] mTileArray; 
    /**게임맵<br/>
     * A two-dimensional array of integers in which the number represents the
     * index of the tile that should be drawn at that locations
     */
    private int[][] mTileGrid;

    private final Paint mPaint = new Paint();

    public TileView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TileView);
        mTileSize = a.getInt(R.styleable.TileView_tileSize, 12);
        a.recycle();
    }
    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TileView);
        mTileSize = a.getInt(R.styleable.TileView_tileSize, 12);
        a.recycle();
    }
    /**
     * Rests the internal array of Bitmaps used for drawing tiles, and
     * sets the maximum index of tiles to be inserted
     * 
     * @param tilecount
     */
    public void resetTiles(int tilecount) {
    	mTileArray = new Bitmap[tilecount];
    }
    /**화면에 들어갈 타일의 갯수를 계산*/
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mXTileCount = (int) Math.floor(w / mTileSize);
        mYTileCount = (int) Math.floor(h / mTileSize);

        mXOffset = ((w - (mTileSize * mXTileCount)) / 2);
        mYOffset = ((h - (mTileSize * mYTileCount)) / 2);

        mTileGrid = new int[mXTileCount][mYTileCount];
        clearTiles();
    }
    /** 이미지 생성 <br/>
     * Function to set the specified Drawable as the tile for a particular
     * integer key.
     * 
     * @param key
     * @param tile
     */
    public void loadTile(int key, Drawable tile) {
        Bitmap bitmap = Bitmap.createBitmap(mTileSize, mTileSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, mTileSize, mTileSize);
        tile.draw(canvas);
        
        mTileArray[key] = bitmap;
    }
    /** 모든 타일 제거<br/>
     * Resets all tiles to 0 (empty)
     */
    public void clearTiles() {
        for (int x = 0; x < mXTileCount; x++) {
            for (int y = 0; y < mYTileCount; y++) {
                setTile(0, x, y);
            }
        }
    }
    /** 게임 맵의 특정 위치에 tileindex 값을 저장 <br/>
     * Used to indicate that a particular tile (set with loadTile and referenced
     * by an integer) should be drawn at the given x/y coordinates during the
     * next invalidate/draw cycle.
     * 
     * @param tileindex
     * @param x
     * @param y
     */
    public void setTile(int tileindex, int x, int y) {
        mTileGrid[x][y] = tileindex;
    }
    /** 설정된 tileindex 값으로 게임맵을 그린다 */
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int x = 0; x < mXTileCount; x += 1) {
            for (int y = 0; y < mYTileCount; y += 1) {
                if (mTileGrid[x][y] > 0) {
                    canvas.drawBitmap(mTileArray[mTileGrid[x][y]], 
                    		mXOffset + x * mTileSize,
                    		mYOffset + y * mTileSize,
                    		mPaint);
                }
            }
        }

    }

}
