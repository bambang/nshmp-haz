Example 6: Enhanced output
--------------------------

__Working directory:__ `/path/to/nshmp-haz/etc/examples/6-enhanced-output`

While mean hazard is of broad interest, it can be useful to preserve individual components of a total curve, particularly with more complex models. Execute the following to write curves for each source type and ground motion model (GMM) used in the 2008 NSHM:

```Shell
hazard ../../../../nshm-cous-2008/Western\ US sites.geojson config.json
```

The [config](https://github.com/usgs/nshmp-haz/blob/master/etc/examples/6-enhanced-output/config.json) file for this example specified `GMM` and `SOURCE` as [output data types](https://github.com/usgs/nshmp-haz/wiki/configuration#calculation-configuration-parameters). Note that the output curves directory now contains additional directories of curves by source type and GMM. We also specified an [output flush limit](https://github.com/usgs/nshmp-haz/wiki/configuration#calculation-configuration-parameters) of `1`. Doing so gives feedback on how long it takes each site calculation to run on a particular system.

See the `nshmp-haz` wiki and Javacocs for more information on source types ([Wiki](https://github.com/usgs/nshmp-haz/wiki/source-types), [JavaDoc](http://usgs.github.io/nshmp-haz/javadoc/index.html?gov/usgs/earthquake/nshmp/eq/model/SourceType.html)) and GMMs ([Wiki](https://github.com/usgs/nshmp-haz/wiki/ground-motion-models), [JavaDoc](http://usgs.github.io/nshmp-haz/javadoc/index.html?gov/usgs/earthquake/nshmp/gmm/Gmm.html)).

__Results directory structure:__
```
6-enhanced-output/
  └─ hazout/
      ├─ config.json
      ├─ HazardCalc.log
      ├─ PGA/
      │   ├─ curves.csv
      │   ├─ gmm/
      │   │   ├─ AB_03_CASCADIA_SLAB/
      │   │   │   └─ curves.csv
      │   │   ├─ ...
      │   │   └─ ZHAO_06_INTERFACE/
      │   │       └─ curves.csv
      │   └─ source/
      │       ├─ FAULT/
      │       │   └─ curves.csv
      │       ├─ ...
      │       └─ SLAB/
      │           └─ curves.csv
      ├─ SA0P2/
      │   └─ ...
      └─ SA1P0/
          └─ ...
```

#### Next: [Example 7 – Deaggregation](../7-deaggregation)

