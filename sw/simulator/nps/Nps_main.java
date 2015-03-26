package sw.simulator.nps;
interface Nps_main{
    bool_t nps_main_parse_options(int argc, char** argv);
    void nps_main_init(void);
    void nps_main_display(void);
    void nps_main_run_sim_step(void);
    boolean nps_main_periodic(gpointer data __attribute__ ((unused)));}
public class Nps_main implements Nps_main {

}
