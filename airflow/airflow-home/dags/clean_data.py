import os
import pandas as pd

def clean_image_data():
    print("CLEANING IMAGE DATA")
    for subdir, dirs, files in os.walk("/opt/temp"):
        for file in files:
            if file.endswith(".csv") and file.startswith("Image"):
                df = pd.read_csv(os.path.join(subdir, file), compression='gzip')
                #rename columns
                df = df.rename(columns={'ullid': 'ull_id', 'LocalTime[us]': 'local_time', 'EngineCycleId#': 'engine_cycle_id', 'ImageId#': 'image_id', 
                'ImageResult$': 'image_result', 'ImageLengthAnnounced[m]#': 'image_length_announced', 'ImageLength[m]#': 'image_length', 'ImageWidth[m]#': 'image_width',
                'MediaType$': 'media_type', 'PrintMode$': 'print_mode', 'Destination$': 'destination', 'JettedInkCyan[ml]#': 'jetted_ink_cyan', 'JettedInkMagenta[ml]#': 'jetted_ink_magenta',
                'JettedInkYellow[ml]#': 'jetted_ink_yellow', 'JettedInkBlack[ml]#': 'jetted_ink_black', 'AccountedInkCyan[ml]#': 'accounted_ink_cyan', 
                'AccountedInkMagenta[ml]#': 'accounted_ink_magenta', 'AccountedInkYellow[ml]#': 'accounted_ink_yellow', 'AccountedInkBlack[ml]#': 'accounted_ink_black',
                'PaperStepMarker$': 'paper_step_marker'
                })
                for x in df.index:
                    print(df.loc[x, "image_result"])
                    if df.loc[x, "image_result"] == "Failed" or df.loc[x, "image_result"] == "Cancelled":
                        df.drop(x, inplace=True)
                
                #correct date formatting and remove empty cells
                #df['date'] = pd.to_datetime(df['date'])
                #df.dropna(subset=['date'], inplace=True)
                
                print(df.columns)
                df.to_csv("/opt/temp/cleaned_" + file, compression='gzip')

def clean_printcycle_data():
    print("CLEANING PRINT CYCLE DATA")
    for subdir, dirs, files in os.walk("/opt/temp"):
        for file in files:
            if file.endswith(".csv") and file.startswith("PrintCycle"):
                df = pd.read_csv(os.path.join(subdir, file), compression='gzip')
                #rename columns
                df = df.rename(columns={'ullid': 'ull_id', 'LocalTime[us]': 'local_time', 'EngineCycleId#': 'engince_cycle_id',
                'PrintCycleId#': 'print_cycle_id', 'ActionId$': 'action_id', 'Result$': 'result', 'PrintMode$': 'print_mode',
                'Images~': 'images', 'SquareDecimeter[dm2]#': 'square_decimeter'})

                #df['date'] = pd.to_datetime(df['date'])
                #df.dropna(subset=['date'], inplace=True)

                df.to_csv("/opt/temp/cleaned_" + file, compression='gzip')

def clean_mediaprepare_data():
    print("CLEANING MEDIA PREPARE DATA")
    for subdir, dirs, files in os.walk("/opt/temp"):
        for file in files:
            if file.endswith(".csv") and file.startswith("MediaPrepare"):
                df = pd.read_csv(os.path.join(subdir, file), compression='gzip')
                #rename columns
                df = df.rename(columns={'ullid': 'ull_id', 'LocalTime[us]': 'local_time', 'EngineCycleId#': 'engine_cycle_id', 'MediaPrepareId#': 'media_prepare_id',
                'Purpose$': 'purpose', 'Category$': 'category', 'MediaTypeDisplayName$': 'media_type_display_name', 'MediaId$': 'media_id', 'NominalWidth[0.1mm]#': 'nominal_width',
                'Measured#': 'measured', 'SideEdgeLeft[0.1mm]#': 'side_edge_left', 'SideEdgeRight[0.1mm]#': 'side_edge_right', 'WaitingForCleancut#': 'waiting_for_cleancut',
                'ManualCut#': 'manual_cut', 'ManualLoading#': 'manual_loading', 'MediaThickness[um]#': 'media_thickness', 'AutomaticMediaJogging#': 'automatic_media_jogging',
                'AdvanceCorrection#': 'advance_correction', 'MoistProtection#': 'moist_correction', 'PreCurePower#': 'pre_cure_power', 'PostCurePower#': 'post_cure_power',
                'BufferHeaterSetpoint[0.1C]#': 'buffer_heater_setpoint', 'CarriageHeightIndex#': 'carriage_height_index', 'ControlPaperStepCorrection#': 'control_paper_ster_correction',
                'ControlPaperStepPatternType$': 'control_paper_step_pattern_type', 'FanFrictionCorrectionTable~': 'fan_friction_correction_table', 'FanFrictionXCoordTable~': 'fan_frictionxcoord_table',
                'PaperStepCorrectionFactor#': 'paper_step_correction_factor', 'PrinterPlatenTemperature[C]#': 'printer_platen_temperature', 'VacuumSurfaceSettings[0.1%]~': 'vacuum_surface_settings',
                'PrintStrategy#': 'print_strategy', 'RobustBacklit#': 'robust_backlit', 'MediaMinimalSwathDuration[ms]#': 'media_minimal_swath_duration',
                'WindingType$': 'winding_type', 'RollPosition#': 'roll_position', 'PrepareFromDrawer#': 'prepare_from_drawer', 'InsideRoll#': 'inside_roll', 'SmartInkLimit#': 'smart_ink_limit'})
                
                #df['date'] = pd.to_datetime(df['date'])
                #df.dropna(subset=['date'], inplace=True)

                df.to_csv("/opt/temp/cleaned_" + file, compression='gzip')