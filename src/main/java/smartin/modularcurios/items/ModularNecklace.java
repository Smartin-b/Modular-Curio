package smartin.modularcurios.items;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.mickelus.tetra.gui.GuiModuleOffsets;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.module.ItemModule;
import se.mickelus.tetra.module.ItemUpgradeRegistry;
import smartin.modularcurios.ModularCurios;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModularNecklace extends ModularItem implements ICurio {
    public final static String necklaceChain = "necklace/chain";
    public final static String necklaceGem = "necklace/gem";
    private static final Logger logger = LogManager.getLogger();
    private static final GuiModuleOffsets majorOffsets = new GuiModuleOffsets(-13, -1);
    private static final GuiModuleOffsets minorOffsets = new GuiModuleOffsets(3, 19);
    public static final String identifier = "modular_necklace";

    public ModularNecklace() {
        super(new Properties());
        majorModuleKeys = new String[]{necklaceChain};
        minorModuleKeys = new String[]{necklaceGem};
    }

    @Override
    public Collection<ItemModule> getAllModules(ItemStack stack) {
        CompoundTag stackTag = stack.getTag();
        if (stackTag != null) {
            return Stream.concat(Arrays.stream(getMajorModuleKeys()), Arrays.stream(getMinorModuleKeys()))
                    .map(stackTag::getString)
                    .map(ItemUpgradeRegistry.instance::getModule)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @ObjectHolder(ModularCurios.MOD_ID + ":" + identifier)
    public static se.mickelus.tetra.items.modular.impl.ModularDoubleHeadedItem instance;

    @Override
    public String[] getMajorModuleKeys() {
        return this.majorModuleKeys;
    }

    @Override
    public String[] getMinorModuleKeys() {
        return this.minorModuleKeys;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMajorGuiOffsets() {
        return majorOffsets;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GuiModuleOffsets getMinorGuiOffsets() {
        return minorOffsets;
    }

    @Override
    public String getDisplayNamePrefixes(ItemStack itemStack) {
        return "Modular Necklace";
    }

    @Override
    public ItemStack getStack() {
        return null;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        items.add(setupRing("iron", "emerald"));
    }

    private ItemStack setupRing(String base, String gem) {
        ItemStack itemStack = new ItemStack(this);
        IModularItem.putModuleInSlot(itemStack, necklaceChain, "ring/simple_base", "simple_base/", "simple_base/" + base);
        IModularItem.putModuleInSlot(itemStack, necklaceGem, "ring/simple_gem", "simple_gem/", "simple_gem/" + gem);
        IModularItem.updateIdentifier(itemStack);

        return itemStack;
    }
}
