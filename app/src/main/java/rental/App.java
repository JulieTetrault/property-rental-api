package rental;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import rental.infrastructure.binder.AppBinder;

public class App implements Runnable {
  private static final int PORT = 8080;

  public static void main(String[] args) {
    new App().run();
  }

  public void run() {
    Server server = new Server(PORT);
    ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
    ResourceConfig packageConfig = new ResourceConfig().packages("rental").register(new AppBinder());;
    ServletContainer container = new ServletContainer(packageConfig);
    ServletHolder servletHolder = new ServletHolder(container);

    contextHandler.addServlet(servletHolder, "/*");

    try {
      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (server.isRunning()) {
        server.destroy();
      }
    }
  }
}
