import org.lwjgl.*; import org.lwjgl.system.*; import org.lwjgl.util.freetype.*; import java.nio.*; import java.nio.file.*;
import static org.lwjgl.util.freetype.FreeType.*;
public class T {
  static long render(PointerBuffer lib, ByteBuffer font, int flags, int px) {
    PointerBuffer fp = MemoryUtil.memAllocPointer(1);
    if (FT_New_Memory_Face(lib.get(0), font, 0, fp)!=0) throw new RuntimeException("face");
    FT_Face f = FT_Face.create(fp.get(0));
    FT_Set_Pixel_Sizes(f, px, px);
    long h=1469598103934665603L; int tested=0;
    for (int c='a'; c<='z'; c++){ int gi=FT_Get_Char_Index(f,c); if (FT_Load_Glyph(f,gi,flags)!=0) throw new RuntimeException("load");
      if (FT_Render_Glyph(f.glyph(), FT_RENDER_MODE_NORMAL)!=0) throw new RuntimeException("render"); FT_Bitmap b=f.glyph().bitmap(); ByteBuffer buf=b.buffer(b.rows()*Math.abs(b.pitch()));
      for(int i=0;i<buf.capacity();i++){h=(h^buf.get(i))*1099511628211L;} tested++; }
    FT_Done_Face(f); return h; }
  public static void main(String[] a) throws Exception {
    byte[] d = Files.readAllBytes(Path.of("/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf"));
    ByteBuffer font = MemoryUtil.memAlloc(d.length).put(d).flip();
    PointerBuffer lib = MemoryUtil.memAllocPointer(1); FT_Init_FreeType(lib);
    int vanilla = 4194312; int mod = vanilla | 0x2;
    System.out.println("vanilla flags="+vanilla+" modded flags="+mod+" NO_HINTING bit set="+((mod&2)!=0));
    for (int px: new int[]{9,16}) System.out.println(px+"px hinted-hash="+render(lib,font,vanilla,px)+" unhinted-hash="+render(lib,font,mod,px)+" same="+(render(lib,font,vanilla,px)==render(lib,font,mod,px)));
  }
}
