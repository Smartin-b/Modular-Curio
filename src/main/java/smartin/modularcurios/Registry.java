package smartin.modularcurios;


import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import smartin.modularcurios.items.ModularRing;

public class Registry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModularCurios.MOD_ID);
    public static final RegistryObject<Item> Test_TRIPPLE = ITEMS.register("modular_ring", ModularRing::new);

    public static void init(IEventBus bus){
        bus.register(Registry.class);
        ITEMS.register(bus);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
    }
}
