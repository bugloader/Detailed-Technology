package detailedTechnology;

import detailedTechnology.gui.CrucibleScreen;
import detailedTechnology.gui.StoneMileScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class DetailedTechnologyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(DetailedTechnology.crucibleScreenHandler, CrucibleScreen::new);
        ScreenRegistry.register(DetailedTechnology.stoneMileScreenHandler, StoneMileScreen::new);
    }
}