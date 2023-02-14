@Controller
public class Controller {
    private Logger log = LoggerFactory.getLogger(Controller.class);
    @GetMapping("/page_log")
    public String page(Model model) {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
        return "page";
    }
}