public class Point2D  
{
    private double x;
    private double y;

    public Point2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Point2D(Point2D point)
    {
        this.x = point.getX();
        this.y = point.getY();
    }

    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public void setX(double newX)
    {
        x = newX;
    }
    
    public void setY(double newY)
    {
        y = newY;
    }
    
    public void add(Vector2D v)
    {
        x += v.getX();
        y += v.getY();
    }    
}
