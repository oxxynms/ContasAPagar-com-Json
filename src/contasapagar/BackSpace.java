package contasapagar;

/**
 *
 * @author bruno
 */
public class BackSpace {
        private String arg;
        private String out;
    //constructor    
        public BackSpace(String data){
            this.setArg(data);
        }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }
    
    public String bs(){
        StringBuilder sb = new StringBuilder(this.getArg());
        int cmp = sb.length();
        cmp -= 1;
        sb.deleteCharAt(cmp);
        this.setOut(sb.toString());
        return this.getOut();
    }
        
    
}
