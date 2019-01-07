package ml.darkbioc.miproyecto;

public class MainItem
{
    private int texto;
    private int image;

    public MainItem(int text, int imagen) {
        texto = text;
        image = imagen;
    }

    public int getTexto() {
        return texto;
    }

    public int getImage()
    {
        return image;
    }
}
