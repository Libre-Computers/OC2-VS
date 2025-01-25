package com.lpprogramming.minecraft.oc2_vs;

import dev.architectury.platform.forge.EventBuses;
import li.cil.oc2r.api.API;
import li.cil.oc2r.client.ClientSetup;
import li.cil.oc2r.client.manual.Manuals;
import li.cil.oc2r.common.block.Blocks;
import li.cil.oc2r.common.blockentity.BlockEntities;
import li.cil.oc2r.common.bus.device.DeviceTypes;
import li.cil.oc2r.common.bus.device.data.BlockDeviceDataRegistry;
import li.cil.oc2r.common.bus.device.data.FirmwareRegistry;
import li.cil.oc2r.common.bus.device.provider.ProviderRegistry;
import li.cil.oc2r.common.container.Containers;
import li.cil.oc2r.common.entity.Entities;
import li.cil.oc2r.common.item.crafting.RecipeSerializers;
import li.cil.oc2r.common.serialization.ceres.Serializers;
import li.cil.oc2r.common.tags.BlockTags;
import li.cil.oc2r.common.tags.ItemTags;
import li.cil.oc2r.common.util.RegistryUtils;
import li.cil.oc2r.common.util.SoundEvents;
import li.cil.oc2r.common.vm.provider.DeviceTreeProviders;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import li.cil.oc2r.common.bus.device.provider.*;
import net.minecraftforge.registries.IForgeRegistry;
import li.cil.oc2r.api.bus.device.provider.ItemDeviceProvider;
import net.minecraftforge.registries.DeferredRegister;
import java.lang.reflect.*;


@Mod("oc2_vs")
public class OC2VSModForge {
    public OC2VSModForge() {
        EventBuses.registerModEventBus("oc2_vs", FMLJavaModLoadingContext.get().getModEventBus());
        Items.initialize();
        DeferredRegister<ItemDeviceProvider> registry;
        try {
            for (var field : ProviderRegistry.class.getDeclaredFields()) {
                if (field.getName().equals("ITEM_DEVICE_PROVIDERS")) {
                    field.setAccessible(true);
                    registry = (DeferredRegister<ItemDeviceProvider>) field.get(null);
                    registry.register("ship_operations_module", ShipOperationsModuleDeviceProvider::new);
                    return;
                }
            }
        }
        catch (IllegalAccessException e) {
        }
        catch (NullPointerException n) {
        }
        catch (SecurityException e) {
        }
        
    }

}
