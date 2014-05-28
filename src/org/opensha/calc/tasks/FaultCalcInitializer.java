package org.opensha.calc.tasks;

import static java.lang.Math.sin;
import static org.opensha.geo.GeoTools.TO_RAD;

import java.util.List;
import java.util.concurrent.Callable;

import org.opensha.calc.Site;
import org.opensha.eq.fault.surface.RuptureSurface;
import org.opensha.eq.forecast.Distances;
import org.opensha.eq.forecast.FaultSource;
import org.opensha.eq.forecast.Rupture;
import org.opensha.gmm.GMM_Source;

import com.google.common.collect.Lists;

/**
 * Compiles source and site data into a {@code List} of {@code GMM_Source}s.
 * @author Peter Powers
 */
final class FaultCalcInitializer implements Callable<List<GMM_Source>> {

	private final FaultSource source;
	private final Site site;
	
	FaultCalcInitializer(FaultSource source, Site site) {
		this.source = source;
		this.site = site;
	}
	
	// TODO this needs additional rJB distance filtering
	// Is it possible to return an empty list??
	
	@Override
	public List<GMM_Source> call() throws Exception {
		List<GMM_Source> inputs = Lists.newArrayList();
		for (Rupture rup : source) {
			RuptureSurface surface = rup.surface();
			Distances distances = surface.distanceTo(site.loc);
			double dip = surface.dip();
			double width = surface.width();
			double zTop = surface.depth();
			double zHyp = zTop + sin(dip * TO_RAD) * width / 2.0;
			
			GMM_Source input = GMM_Source.create(
				rup.mag(),
				distances.rJB,
				distances.rRup,
				distances.rX,
				dip,
				width,
				zTop,
				zHyp,
				rup.rake(),
				site.vs30,
				site.vsInferred,
				site.z2p5,
				site.z1p0);
			inputs.add(input);
		}
		return inputs;
	}

}