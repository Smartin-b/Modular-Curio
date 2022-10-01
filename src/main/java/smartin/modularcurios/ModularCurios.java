package smartin.modularcurios;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("modularcurios")
public class ModularCurios {

    // Directly reference a log4j logger.
    public static final String MOD_ID = "modularcurios";
    private static final Logger LOGGER = LogManager.getLogger();

    public ModularCurios() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Registry.init(eventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
